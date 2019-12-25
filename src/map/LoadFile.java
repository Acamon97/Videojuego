package map;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

public class LoadFile {

	
	private Map loadedMap;
	private int width;
	private int height;
	private String name = "";
	private String userInput = "";
	private String mapInfo = "";
        
        
        
        
	//Hay que cambiarlo para poder jugar
        
	private static final String folderName = System.getProperty("user.dir") + "\\src\\mapSaves";
                
        
        
	private static final File directory = new File(folderName);
	private static ArrayList<String> files;
	private static ArrayList<Map> mapList;
	
	
	public LoadFile(){
            listFilesforFolder();
		
	}
	
	public void listFilesforFolder(){
		
            files = new ArrayList<>();
            File[] listOfFiles = directory.listFiles();
            for (int i = 0; i < listOfFiles.length; i++){
                if (listOfFiles[i].isFile()) {
                        String name = listOfFiles[i].getName();
                        final int lastPeriodPos = name.lastIndexOf('.');
                        files.add(listOfFiles[i].getName().substring(0, lastPeriodPos));
                } else if (listOfFiles[i].isDirectory()) {

                }
            }
	}
	
	/**
	 * Read and create a map from a text file
	 * @param file
	 * @return
	 * @throws IOException
	 */
	public Map loadFile(String name) throws IOException{
		this.name = name;
                File file = new File(folderName + "/" + name + ".txt");
		FileReader fr = new FileReader(file);
		BufferedReader br =  new BufferedReader(fr);
		int count = 0;
		try {
			StringBuilder sb = new StringBuilder();
			String line = br.readLine();

			while (line != null) {
				count++;
				if (count == 1){
					width = Integer.valueOf(line);
				}
				else if (count == 2){
					height = Integer.valueOf(line);
				}
				else if (count == 3){
					userInput = line.toString();
				}				
				else {
					sb.append(line);
					sb.append(System.lineSeparator());
				}

				line = br.readLine();
			}
			mapInfo = sb.toString();
		} finally {
			br.close();
                        mapList.add(createMap());
		}	
		
		return loadedMap;
	}

	public ArrayList<Map> getAllMap(){
		mapList = new ArrayList<Map>();
		
		for (int i = 0; i < getListofFiles(0).size(); i++){
			try {
				loadFile(getListofFiles(0).get(i));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return mapList;
	}
	
	public Map createMap(){
		loadedMap = new MapEditor(width, height, userInput).getMap();		
		
		return loadedMap;
	}
	
	public ArrayList<String> getListofFiles(int n){
            return files;           
	}

}