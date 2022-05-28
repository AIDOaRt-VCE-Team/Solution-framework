rule metricsForInterface
match s : metrics!Log
with  t : sysMl!Block
{
  compare
  {
    return s.source.split("\\.")[0] = t.name;
  }
}
