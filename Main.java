package startUpTheStartup;

import java.util.*;

public class Main {
	
	static String dashSeparator = "----------";
	
	public static void main(String[] args)
	{	
		Scanner scanner = new Scanner(System.in);
		int loopIterations = scanner.nextInt();
		scanner.nextLine(); //throw away dashes
		for (int i = 0; i < loopIterations; i++)
		{
			printTest(scanner);
			if ((i != loopIterations -1))
				scanner.nextLine(); //throw away empty line
		}
	}
	
	static void printTest(Scanner scanner)
	{
		String string = scanner.nextLine();
		Map<String, Integer> searchStringTerms = getTerms(string);
		String line1 = scanner.nextLine();
		String line2 = scanner.nextLine();
		while (!line2.equals(dashSeparator))
		{
			Map<String, Integer> docStringTerms = getTerms(line2);
			double docScore = 0;
			for (String key : searchStringTerms.keySet())
			{
				if (docStringTerms.containsKey(key))
				{
					docScore += Math.sqrt(searchStringTerms.get(key) * docStringTerms.get(key));
				}
			}
			System.out.printf("%.2f\n", docScore);
			line1 = scanner.nextLine(); //throw away
			line2 = scanner.nextLine();
		}
	}
	
	static Map<String, Integer> getTerms(String str)
	{
		String[] tokens = str.split(" ");
		Map<String, Integer> terms = new HashMap<String, Integer>();
		for(int j = 0; j < tokens.length; j++)
		{
			if (!terms.containsKey(tokens[j]))
			{
				terms.put(tokens[j].replaceAll("[^A-Za-z0-9]", ""), 1);
			}
			else
			{
				terms.put(tokens[j].replaceAll("[^A-Za-z0-9]", ""), terms.get(tokens[j].replaceAll("[^A-Za-z0-9]", ""))+1);
			}
		}
		return terms;
	}
}
