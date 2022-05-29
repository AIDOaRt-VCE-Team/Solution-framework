rule ProblematicUnit
match s : metrics!Log
with  t : sysMl!DumperBody
{
  compare
  {
    return s.source = t.fgiCaf;
  }
}
rule SystemToUse
match s : metrics!Log
with  t : uml!Comment
{
  compare
  {
    return t.eContainer().name = "Controller";
  }
}
