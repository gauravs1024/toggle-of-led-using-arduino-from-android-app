#include<ESP8266WiFi.h>

WiFiClient client;
WiFiServer server(80);
#define led1 D1
#define led2 D2
void setup() {
  // put your setup code here, to run once:
  Serial.begin(115200);
WiFi.begin("Nextup Robotics.","king@123");
while(WiFi.status()!=WL_CONNECTED)
{
  Serial.println("connecting...");
  delay(200);
}
Serial.println();
Serial.println("NODEMCU is connected succesfully");
Serial.println(WiFi.localIP());
server.begin();
pinMode(led1,OUTPUT);
pinMode(led2,OUTPUT);


}
String request;
void loop() {
 
  // put your main code here, to run repeatedly:
  client=server.available();
  if(client==1){
    request=client.readStringUntil('\n');
    Serial.println(request);
    request.trim();
  }
   if(request=="GET /onled1 HTTP/1.1"){
    digitalWrite(led1,HIGH);}
    

 else if(request=="GET /offled1 HTTP/1.1"){
    digitalWrite(led1,LOW);
   }
   else if(request=="GET /onled2 HTTP/1.1"){
    digitalWrite(led2,HIGH);
   }
   else if(request=="GET /offled2 HTTP/1.1"){
    digitalWrite(led2,LOW);
   }
    
 
    

}
