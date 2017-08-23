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
        switch (pickName)
        {
            case "Ettor":
                return pickName + " " + num[0] + "st";
            case "Tvåor":
                return pickName + " " + num[0] + "st";
            case "Treor":
                return pickName + " " + num[0] + "st";
            case "Fyror":
                return pickName + " " + num[0] + "st";
            case "Femmor":
                return pickName + " " + num[0] + "st";
            case "Sexor":
                return pickName + " " + num[0] + "st";
        }
        return pickName;
    }
}
