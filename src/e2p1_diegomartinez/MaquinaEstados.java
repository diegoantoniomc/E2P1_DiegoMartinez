package e2p1_diegomartinez;

import java.util.ArrayList;

public class MaquinaEstados {
    
    private ArrayList<String> estados =new ArrayList();
    private ArrayList<String> estados_aceptacion = new ArrayList();
    private ArrayList<String> aristas = new ArrayList();
    private String estado_actual;

    public ArrayList<String> getEstados() {
        return estados;
    }

    public void setEstados(ArrayList<String> estados) {
        this.estados = estados;
    }

    public ArrayList<String> getEstados_aceptacion() {
        return estados_aceptacion;
    }

    public void setEstados_aceptacion(ArrayList<String> estados_aceptacion) {
        this.estados_aceptacion = estados_aceptacion;
    }

    public ArrayList<String> getAristas() {
        return aristas;
    }

    public void setAristas(ArrayList<String> aristas) {
        this.aristas = aristas;
    }

    public String getEstado_actual() {
        return estado_actual;
    }

    public void setEstado_actual(String estado_actual) {
        this.estado_actual = estado_actual;
    }
    
    public MaquinaEstados (String estados, String aristas){
        this.estados=splitStr(estados, ';');
        extractAcceptNodes();
        this.aristas = splitStr(aristas, ';');
        this.estado_actual = this.estados.get(0);
    }
    
    public ArrayList<String> splitStr(String str, char delim){
        ArrayList<String> split = new ArrayList();
        String [] x = str.split(String.valueOf(delim));
        for (int i = 0; i < x.length; i++) {
            split.add(x[i]);
        }
        return split;
    }//End splitStr
    
    public void extractAcceptNodes(){
        for (String temp: this.estados) {
            if(temp.contains(".")){
                String aceptados=temp.substring(1,3);
                this.estados.set(this.estados.indexOf(temp), aceptados);
                estados_aceptacion.add(aceptados);
            }//End if
        }//End for
    }//End extractAcceptNodes
    
    public String getArista(String str){
        for (int i = 0; i < aristas.size(); i++) {
            if(aristas.get(i).contains(str)){
                return aristas.get(i);
            }//End it
        }//End for
        return "";
    }//End getArista
    
    public String computeStr(String str){
        String output = "";
        estado_actual=estados.get(0);
        for (int i = 0; i < str.length(); i++) {
            String send = estado_actual + ','+str.charAt(i);
            String temp = getArista(send);
            
            if (!temp.equals("")){
                output+= estado_actual + ": " + str.charAt(i) + " -> "+temp.split(",")[2]+ "\n";
                estado_actual = temp.split(",")[2];
            }
            else{
                output = "La cadena no es valida. Rechazada";
            }
        }
        if (estados_aceptacion.contains(estado_actual)){
            output+="Aceptada";
        }//End if
        else{
            output+="Rechazada";
        }
        return output;
    }//End computeStr
    
    
    
}
