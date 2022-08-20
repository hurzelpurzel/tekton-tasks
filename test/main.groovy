import groovy.yaml.YamlBuilder;
import groovy.yaml.YamlSlurper;




def loadInput(filename){
  def workspace = System.getenv('WORKSPACE_SOURCE')
  def ys = new YamlSlurper()
  File fh1 = new File(workspace+"/"+ filename)
  text = fh1.getText('UTF-8')
  return ys.parseText(text);
}

def writeOutput(filename, output){
   def workspace = System.getenv('WORKSPACE_SOURCE')
   def builder = new YamlBuilder()
   builder {out : output}
   text = builder.toString()

   println text
   
   File file = new File(workspace+"/"+ filename)
   file.write text
}

def output = [:]
output.test = "Testausgabe"


println 'This is the Groovy world!'

writeOutput( "out.yaml" , output)