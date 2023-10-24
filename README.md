# Singtel AEM project

This is a project for Singtel Mega Menu

AEM 6.5 & Service pack 6.5.17 used for both author and dispatcher 

## Modules

The main parts of the project are:

* Mega Menu Component - /apps/singtel/components/content/megaMenu
* Mega Menu Page Component - /apps/singtel/components/strucutre/page
* Mega Menu Template - /conf/singtel/settings/wcm/templates/page-content
* Mega Menu Sling Model - MegaMenuModel.java
* OSGI Config File that included in Run Mode Publish and include Sling Dynamic Include with SSI for Mega Menu /apps/singtel/osgiconfig/config.publish/org.apache.sling.dynamicinclude.Configuration-megamenusdi.config
* Unit Tests Class - MegaMenuModelTest.java (97%) code coverage & 0 sonar



## How to build

To build all the modules and deploy the `all` package to a local instance of AEM, run in the project root directory the following command:

    mvn clean install -PautoInstallSinglePackage

Or to deploy it to a publish instance, run

    mvn clean install -PautoInstallSinglePackagePublish


### Unit tests

 MegaMenuModelTest.java (97%)

### Sonar Check

0 Blocker
0 Critical
0 Major

### Caching Strategy ####

Current Strategy - We have used SDI with SSI. It will include Mega Menu with SSI directive on dispatcher with "nocache.html". Please see Dispatcher project for caching rules.

Alternative Strategy - we can add a reosuece change listener on publish that we clean parent age and parent of paraent page cache if we need to cache mega menu section also

### Testing ###

Open Page www-testing.org/content/singtel/us/en/home.html (See Dispatcher project for this)

It will show

Menu - Car => Sub Menu -> Kia Motors / Volkswagen / Mercedes Benz with links
Menu - Bike => Sub Menu -> Hero Motors / Harley Davidson


Hide In Nave and Hide All Sub Nav page properties are added

Mega Menu available on all pages 
