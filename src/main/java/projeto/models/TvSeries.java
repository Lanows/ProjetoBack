package projeto.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "tb_tvSeries")
public class TvSeries extends Program  {

    @Column(name = "cl_seasons")
    private int seasons;

    public TvSeries() {
    }

    public TvSeries(Long id, Long idTMDB, String backdropPath, @Size(min = 2, max = 50) String title, @Size(min = 1, max = 1500) String overview, @Size(min = 1, max = 30) String country, @Size(min = 1, max = 30) String language, Date releaseDate, int runtime, Set<Genre> genres, Set<Participation> participations, int seasons) {
        super(id, idTMDB, backdropPath, title, overview, country, language, releaseDate, runtime, genres, participations);
        this.seasons = seasons;
    }

    public int getSeasons() {
        return seasons;
    }

    public void setSeasons(int seasons) {
        this.seasons = seasons;
    }
}
