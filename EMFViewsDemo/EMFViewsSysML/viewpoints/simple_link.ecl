rule problematicBlock
match s : UML!Class
with  t : CAEX!InternalElement
{
  compare
  {
    return s.name = t.name;
  }
}
