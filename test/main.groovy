import groovy.yaml.YamlBuilder;
import groovy.yaml.YamlSlurper;


def workspace = System.getenv('WORKSPACE_SOURCE')


def loadInput(filename){
  def ys = new YamlSlurper()
  File fh1 = new File(workspace+"/"+ filename)
  text = fh1.getText('UTF-8')
  return ys.parseText(text);
}

def writeOutput(filename, output){
   def builder = new YamlBuilder()
   builder.out = output
   File file = new File(workspace+"/"+ filename)
   file.write builder.toString()
}

def output = [:]
output.test = "Testausgabe"


println 'This is the Groovy world!'

writeOutput( "out.yaml" , output)