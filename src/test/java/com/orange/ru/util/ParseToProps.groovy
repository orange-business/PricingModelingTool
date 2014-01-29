package com.orange.ru.util

class ParseToProps {
  Properties parse(String path) {
    def props = new Properties()
    String text = new File(path).getText("UTF-8")
    text.eachLine { line ->
      if (line.indexOf(' ') > 2)  {
        int index = line.indexOf(' ')
        props.put(line.substring(0,index), line.substring(index+1))
      }
    }
    return props
  }
}