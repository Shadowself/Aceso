/*
 *
 *  * Copyright (C) 2017 meili-inc company
 *  *
 *  * Licensed under the Apache License, Version 2.0 (the "License");
 *  * you may not use this file except in compliance with the License.
 *  * You may obtain a copy of the License at
 *  *
 *  *      http://www.apache.org/licenses/LICENSE-2.0
 *  *
 *  * Unless required by applicable law or agreed to in writing, software
 *  * distributed under the License is distributed on an "AS IS" BASIS,
 *  * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  * See the License for the specific language governing permissions and
 *  * limitations under the License.
 *
 */

package com.mogujie.aceso.util

import com.android.build.gradle.internal.pipeline.TransformTask
import org.gradle.api.Project

import static com.mogujie.aceso.Constant.ACESO_DIR
import static com.mogujie.aceso.Constant.INTERMEDIATES_DIR

/**
 * A Util.
 *
 * @author wangzhi
 */
public class GradleUtil {

    public static def getAndroidSdkPath(Project project) {
        return "${project.android.getSdkDirectory()}/platforms/${project.android.getCompileSdkVersion()}/android.jar"
    }

    /**
     * Whether it is executed acesoXXX task.
     */
    public static boolean isAcesoFix(Project project) {
        boolean isNewHotfix = project.gradle.getStartParameter().taskNames.any { taskName ->
            if (taskName.toLowerCase().startsWith("aceso")) {
                return true
            }
        }
        return isNewHotfix
    }

    public static boolean isProguardOpen(def variant) {
        return variant.getBuildType().buildType.minifyEnabled
    }

    public static TransformTask getProguardTask(Project project, String varName) {
        return project.tasks.findByName("transformClassesAndResourcesWithProguardFor${varName}")
    }

    public static File getFileInAceso(Project project, String category, String varDirName, String fileName) {
        if (FileUtils.isStringEmpty(fileName)) {
            return FileUtils.joinFile(project.buildDir, INTERMEDIATES_DIR, ACESO_DIR, category, varDirName)
        } else {
            return FileUtils.joinFile(project.buildDir, INTERMEDIATES_DIR, ACESO_DIR, category, varDirName, fileName)
        }
    }


}
