
package cinema;


import com.omertron.themoviedbapi.model.MovieDb;
import java.io.IOException;
import java.util.List;
import javax.swing.DefaultListModel;
import javax.swing.ListModel;

public class CtrlGUI {
    
    private FachadaServicoTmdb fachadaCine;
    
    public CtrlGUI() throws IOException, FachadaServicoTmdbException{
        fachadaCine = new FachadaServicoTmdb("5a1a77e2eba8984804586122754f969f");
    }
         
    public ListModel getNomeDeFilmes( String nome ) throws FachadaServicoTmdbException{
        DefaultListModel lm = new DefaultListModel();
        
        List<MovieDb> resultado = fachadaCine.buscarFilmes(nome);
        for (MovieDb filme : resultado) {
            lm.addElement(filme.getTitle());
        }
        return lm;
    }
        
}
