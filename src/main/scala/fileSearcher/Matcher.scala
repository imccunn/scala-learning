package fileSearcher

import java.io.File

class Matcher(filter: String, val rootLocation: String = new File(".").getCanonicalPath(), checkSubFolders:Boolean = false) {
   val rootIOObject = FileConverter.convertToIOObject(new File(rootLocation))

   def execute() = {
     def recursiveMatch(files:List[IOObject], currentList:List[FileObject]): List[FileObject] =
        files match {
          case List() => currentList
          case iOObject :: rest =>
            iOObject match {
              case file: FileObject if FilterChecker(filter) matches file.name =>
                recursiveMatch(rest, file :: currentList)
              case directory: DirectoryObject =>
                recursiveMatch(rest ::: directory.children(), currentList)
              case _ => recursiveMatch(rest, currentList)
            }
        }

     val matchedFiles = rootIOObject match {
       case file : FileObject if FilterChecker(filter) matches file.name => List(file)
       case directory : DirectoryObject =>
         if(checkSubFolders) recursiveMatch(directory.children(), List())
         else FilterChecker(filter) findMatchedFiles directory.children()
       case _ => List()
     }

     matchedFiles map(iOObject => iOObject.name)
   }
}
