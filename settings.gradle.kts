pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "Rekomendasi Beli App"
include(":app")
include(":roomDb")
//include(":naiveBayes")
//include(":core")
include(":data")
include(":domain")
