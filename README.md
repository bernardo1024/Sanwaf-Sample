# Sanwaf Framework Overview
 Sanwaf is a declarative data validation framework that secures your UI & Server without writing any code

- [Sanwaf-UI](https://github.com/bernardo1024/Sanwaf-UI) is a Sanitation Web Application Firewall that runs on the Browser
        
       - Uses a declarative mechanism to add validation to HTML pages
       - Add validation to a UI elements by including custom Sanwaf-UI Attributes
       - Fully configurable look and feel
       - No custom code is required to perform validation on web pages

-  [Sanwaf-Server](https://github.com/bernardo1024/Sanwaf-Server) is a Sanitation Web Application Firewall that runs on the Server
        
       - Sanwaf-Server secures parameters, cookies, headers and endpoints prior to entering your application code
       - Sanwaf-Server is configured with an XML file
       - Can be used independently of Sanwaf-UI
       - No custom code is required to perform validation on the server

- [Sanwaf-UI-2-Server](https://github.com/bernardo1024/Sanwaf-UI-2-Server) Utility converts the Sanwaf-UI declarative validation into the server XML format
        
       - Provides for effortless Sanwaf-Server configuration using Sanwaf-UI attributes
       - Converts the Sanwaf-UI declarative Attributes into a Sanwaf-Server consumable form
       - Automate Sanwaf-Server configuration using this utility

- [Sanwaf-Sample](https://github.com/bernardo1024/Sanwaf-Sample) project is a sample implementation of Sanwaf-UI and Sanwaf Server

       - End-2-end sample of using Sanwaf-UI & Sanwaf-Server
       - Dynamically configure and test Sanwaf-UI 
       - Dynamically disable Browser Validation and run against Server (uses embedded Jetty)

# Sanwaf Sample Application

1. Build the application

        mvn clean package

2. Run the sample application, in the SanwafSample project:
 
        mvn jetty:run

3. Open a browser and navigate to [http://127.0.0.1:8080](http://127.0.0.1:8080)

        http://127.0.0.1:8080

4. if you update sanwaf attributes on html sample pages, you will need to run the sanwaf-ui-2-server mvn command, then restart jetty

        mvn exec:exec

if you experience any exception relating to the sanwaf-server dependency, see the readme.md in the Sanwaf-Server project for instructions on cloning and installing the jar to your local maven repo
