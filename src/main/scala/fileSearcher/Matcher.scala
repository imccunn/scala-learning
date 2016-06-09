package fileSearcher

import java.io.File

class Matcher(filter: String, val rootLocation: String = new File(".").getCanonicalPath()) {
   val rootIOObject = FileConverter.convertToIOObject(new File(rootLocation))
   
   def execute() = {
     val matchedFiles = rootIOObject match {
       case file : FileObject if FilterChecker(filter) matches file.name => List(file)
       case directory : DirectoryObject => 
         FilterChecker(filter) findMatchedFiles directory.children()
       case _ => List()
     }
     
     matchedFiles map(iOObject => iOObject.name)
   }
}