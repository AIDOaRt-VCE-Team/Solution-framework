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
with  t : uml!Component
{
  compare
  {
    return t.name = "Controller";
  }
}
