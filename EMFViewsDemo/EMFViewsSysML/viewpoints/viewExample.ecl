rule ProblematicDumperBody
match s : metrics!Log
with  t : sysMl!DumperBody
{
  compare
  {
    return s.source = t.fgiCaf;
  }
}
rule ProblematicTailGate
match s : metrics!Log
with  t : sysMl!Tailgate
{
  compare
  {
    return s.source = t.fgiCaf;
  }
}
rule CommentFromComponent
match s : metrics!Log
with  t : uml!Comment
{
  compare
  {
    return t.eContainer().name = "ExternalSensor - 951" or t.eContainer().name = "InternalSensor - 951";
  }
}
