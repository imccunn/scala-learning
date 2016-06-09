package fileSearcher

import fileSearcher.IOObject

class FilterChecker(filter: String) {
  def matches(content : String) = content contains filter
  
  def findMatchedFiles(iOObjects : List[IOObject]) = {
    for (iOObject <- iOObjects
        if(iOObject.isInstanceOf[FileObject])
        if(matches(iOObject.name)))
      yield iOObject
  }
}

// This is called a companion object. Singleton or static class methods can be called without 'new'. 
object FilterChecker {
  def apply(filter: String) = new FilterChecker(filter)
}