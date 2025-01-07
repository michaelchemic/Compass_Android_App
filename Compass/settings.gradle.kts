pluginManagement {
    repositories {
        maven { url = uri("https://maven.aliyun.com/repository/public/") }
        maven { url = uri ("https://maven.aliyun.com/repository/public") } // 阿里云 Maven 中央仓库
        maven { url = uri ("https://maven.aliyun.com/repository/google") } // 阿里云 Maven 中央仓库
        maven { url = uri ("https://maven.aliyun.com/repository/jcenter") } // 阿里云 Maven 中央仓库

        maven { url = uri("https://jitpack.io") }
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        maven { url = uri("https://maven.aliyun.com/repository/public/") }
        maven { url = uri ("https://maven.aliyun.com/repository/public") } // 阿里云 Maven 中央仓库
        maven { url = uri ("https://maven.aliyun.com/repository/google") } // 阿里云 Maven 中央仓库
        maven { url = uri ("https://maven.aliyun.com/repository/jcenter") } // 阿里云 Maven 中央仓库

        maven { url = uri("https://jitpack.io") }
        google()
        mavenCentral()
    }
}

rootProject.name = "Compass"
include(":app")
 