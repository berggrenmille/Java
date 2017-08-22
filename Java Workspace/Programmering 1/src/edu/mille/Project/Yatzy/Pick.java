package edu.mille.Project.Yatzy;

public class Pick
{
    public String pickName;
    public int[] num;

    public Pick(String name)
    {
        pickName = name;
    }

    public Pick(String name,int[] _num)
    {
        pickName = name;
        num = _num;
    }

    @Override
    public String toString() {
        return pickName;
    }
}
