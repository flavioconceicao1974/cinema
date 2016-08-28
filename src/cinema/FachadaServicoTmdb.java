package cinema;

import com.omertron.themoviedbapi.MovieDbException;
import com.omertron.themoviedbapi.TheMovieDbApi;
import com.omertron.themoviedbapi.model.MovieDb;
import com.omertron.themoviedbapi.results.TmdbResultsList;
import java.util.List;

public class FachadaServicoTmdb {

    private String chaveAcesso;
    private TheMovieDbApi tmdb;

    public FachadaServicoTmdb(String chaveAcesso) throws FachadaServicoTmdbException {
        this.chaveAcesso = chaveAcesso;
        try {
            tmdb = new TheMovieDbApi(chaveAcesso);
        } catch (MovieDbException ex) {
            throw new FachadaServicoTmdbException("Erro de inicialização da fachada de acesso ao TMDB;", ex);
        }
    }

    public List<MovieDb> buscarFilmes(String nome) throws FachadaServicoTmdbException {
        try {
            TmdbResultsList<MovieDb> filmes = tmdb.searchMovie(nome, 0, "", true, 0);
            return filmes.getResults();
        } catch (MovieDbException ex) {
            throw new FachadaServicoTmdbException("Falha na busca por filmes.", ex);
        }
    }

    public MovieDb getFilme(String nome) throws FachadaServicoTmdbException {
        List<MovieDb> resultado = this.buscarFilmes(nome);
        for (MovieDb movie : resultado) {
            if (movie.getTitle().equals(nome)) {
                return movie;
            }
        }
        return null;
    }
}
