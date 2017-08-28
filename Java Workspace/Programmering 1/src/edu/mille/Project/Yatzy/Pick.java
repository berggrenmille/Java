package edu.mille.project.yatzy;

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
            case "Ett par":
                return  pickName + ": " + num[0] + ":or";
            case "Två par":
                return  pickName + ": "+ num[0] + ":or och " + num[1] + ":or";
            case "Tretal":
                return  pickName + ": " + num[0] + ":or";
            case "Fyrtal":
                return  pickName + ": " + num[0] + ":or";
            case "Lilla Stege":
                return  pickName;
            case "Stora Stege":
                return pickName;
            case "Kåk":
                return pickName + ": " + num[0] +":or + 2: " + num[1] + ":or";
            case "Chans":
                return  pickName;
            case "Yatzy":
                return "YATZY!!!";
            default:
                return "Faulty name";
        }
    }
}
