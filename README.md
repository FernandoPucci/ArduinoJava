# ArduinoJava
Java Codes to interfacing with Arduino

This applications Runs a Web page (JSF + GlassFish) with controllers that sent to Arduino's board through RMI client/server interface, which control arduino's board by serial commands. 

ArduinoWeb: Web Page with graphical controls; Back-end enabled to send RMI Messages to RMI Server

RMI Server: Responsible to catch commands from web page and send to Serial Port

RMI Client: test messaging Aplication

-----------------------------------------------------------------------------

ArduinoWeb-> RMI Server (always running after GlassFish start and during utilization) -> Arduino Board

This software works into a Eclipse Workspace.

The folder 'HardwareCodes' contains the code to use into Arduino's board.
