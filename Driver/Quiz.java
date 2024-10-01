package Driver;
public class Quiz 
{
    private MultipleChoice Q1 = new MultipleChoice("Which class is taught by Dr. Menuge?", new String[]{"Coding 3", "Computational Dilemmas", "Computer Architecture", "User Experience"}, 1);
    private MultipleChoice Q2 = new MultipleChoice("Which language doesn't require semicolons?", new String[]{"Java", "C", "C#", "Visual Basic"}, 3);
    private MultipleChoice Q3 = new MultipleChoice("Select the strongly-typed language.", new String[]{"Python", "Scheme", "C++", "JavaScript"}, 2);
    private MultipleChoice Q4 = new MultipleChoice("Who ceated Linux?", new String[]{"Ken Thompson", "Bill Gates", "Linus Torvalds", "Steve Jobs"}, 2);
    private MultipleChoice Q5 = new MultipleChoice("Who is the creator of Git Version Control?", new String[]{"Ada Lovelace", "Linus Torvalds", "Gary Locklair", "Stephen J. Gould"}, 1);
    private MultipleChoice Q6 = new MultipleChoice("What does HTML stand for?", new String[]{"HyperText Markup Language", "High-Level Text Markup Language", "HyperText Multi Language", "HyperText Machine Language"}, 0);
    private MultipleChoice Q7 = new MultipleChoice("Who formulated the idea of base-two mathematics?", new String[]{"Alan Turing", "Ada Lovelace", "George Boole", "Gottfried W. Leibniz"}, 3);
    private MultipleChoice Q8 = new MultipleChoice("What language is Linux written in?", new String[]{"Java", "C", "C#", "C++"}, 1);
    private MultipleChoice Q9 = new MultipleChoice("What assembly language is supported by the Nintendo 64 architecture?", new String[]{"MIPS ASM", "x86 ASM", "Z80 ASM", "6502 ASM"}, 0);
    private MultipleChoice Q10 = new MultipleChoice("Which language was created in the early 1970s?", new String[]{"Java", "C", "Python", "LOLCODE"}, 1);
    // remember the questions have to relate to each other. 
    private MultipleChoice[] Content = {Q1, Q2, Q3, Q4, Q5, Q6, Q7, Q8, Q9, Q10};

    public MultipleChoice[] getContent() {
        return Content;
    }
}
