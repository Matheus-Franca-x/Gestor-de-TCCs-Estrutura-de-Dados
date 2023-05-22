package telaController;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


import br.fatec.ListString.ListString;

public class ComboBoxController 
{
	private String arquivoArea;
	private ListString[] listaSubArea;
	
	
	
	
	
	public ComboBoxController(ListString[] listaSubArea) 
	{
		this.listaSubArea = listaSubArea;
		this.arquivoArea = getArquivoArea();
		
	}
	

	private int hashCodeArea(String numero)
	{
		int posit = Integer.parseInt(numero.substring(0, 1));
		
		return posit;
	}

	private String[] selecionaArea(String arquivoArea) throws Exception
	{
		
		File arq = new File(arquivoArea);
		
		if (arq.exists() && arq.isFile())
		{
//			File file = openExistentFile(arquivoArea);
			
			
			
	        FileReader lerFlux = new FileReader(arq);
			BufferedReader buffer = new BufferedReader(lerFlux);
			String linha = buffer.readLine();
			StringBuilder content = new StringBuilder();
			
			
			while(linha != null)
			{
				content.append(linha).append("\n");
	            linha = buffer.readLine();
			}
			
			return content.toString().split("\n");
			
		}
		
		return null;
	}
	
	private String getArquivoArea()
    {
        String caminhoRaiz, caminhoArquivo;

        caminhoRaiz = System.getProperty("user.home") + File.separator;
        caminhoRaiz += "TEMP" + File.separator;
        caminhoArquivo = caminhoRaiz + "ProfessorAreasdeAtuacao.csv";

        return caminhoArquivo;
    }
	
	
	public String[] area() throws Exception
	{
		String[] area = selecionaArea(arquivoArea);
		
		listaSubArea = new ListString[area.length - 1];
		int tamArea = area.length;
		
		for (int i = 0; i < tamArea - 1; i++)
		{
			listaSubArea[i] = new ListString();
		}

		String[] areaFormatted = new String[tamArea - 1];
		for (int i = 0; i < tamArea - 1; i++)
		{
			StringTokenizer stArea = new StringTokenizer(area[i + 1], ",");
			
			areaFormatted[i] = stArea.nextToken();
			
			int hash = hashCodeArea(areaFormatted[i]);
			
			
			while(stArea.hasMoreTokens())
			{
				if (listaSubArea[i].isEmpty())
					listaSubArea[hash - 1].addFirst(stArea.nextToken());
				else
					try {
						listaSubArea[hash - 1].addLast(stArea.nextToken());
					} catch (Exception e1) {
						e1.printStackTrace();
					}
			}
		}
		
		return areaFormatted;
		
	}
	
	public String[] subArea()
	{
		
		int tama = 0;
		for (int i = 0; i < listaSubArea.length; i++)
		{
			tama += listaSubArea[i].size();
		}
		String[] subArea = new String[tama];
		int cont = 0;
		
		for (int i = 0; i < listaSubArea.length; i++)
		{
			for (int j = 0; j < listaSubArea[i].size(); j++)
			{
				try {
					subArea[cont] = listaSubArea[i].get(j);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				cont++;
			}
		}
		
		return subArea;
		
	}
	
	public ListString[] pegaList()
	{
		return this.listaSubArea;
	}
	
}