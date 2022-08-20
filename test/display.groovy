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
   
   text = builder(output)

   println text

   File file = new File(workspace+"/"+ filename)
   file.write text
}





def in = loadInput( "out.yaml" )
println "found -->"
print in
