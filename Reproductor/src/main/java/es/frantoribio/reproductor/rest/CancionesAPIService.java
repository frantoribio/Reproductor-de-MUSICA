package es.frantoribio.reproductor.rest;

import es.frantoribio.reproductor.models.Album;
import es.frantoribio.reproductor.models.Artist;
import es.frantoribio.reproductor.models.Song;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

import java.util.List;

public interface CancionesAPIService {
    @GET("/artists")
    Call<List<Artist>> getArtists(@Query("limit") int limit, @Query("sort") String sort);
    @GET("/artists")
    Call<List<Artist>> getArtistsAll();
    @GET("/songs")
    Call<List<Song>> getSongs(@Query("limit") int limit, @Query("sort") String sort);
    @GET("/songs/{id}")
    Call<List<Song>> getSong(@Path("id") int id);
    @GET("/albums")
    Call<List<Album>> getAlbums(@Query("limit") int limit, @Query("sort") String sort);
}


