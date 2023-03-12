package es.frantoribio.reproductor.controllers;

import es.frantoribio.reproductor.models.Song;
import es.frantoribio.reproductor.rest.CancionesAPIService;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import static es.frantoribio.reproductor.utils.Config.API_URL;

public class MainViewController implements Initializable {
    @FXML
    private ListView<Song> listView;
    @FXML
    private Button buttonCargarCanciones;
    @FXML
    private TextField titleLabel;
    @FXML
    private TextField artistLabel;
    @FXML
    private TextField fileLabel;
    @FXML
    private ImageView imagen;
    @FXML
    private Button salir;
    @FXML
    private Button inicioCancionButton;
    @FXML
    private Button playButton;
    @FXML
    private Button finalCancionButton;
    @FXML
    private Button pauseButton;
    @FXML
    private Slider progresBar;
    private CancionesAPIService service;
    public Media media;
    public MediaPlayer mediaPlayer;

    public void play(Song song) {

        if (media == null) {
            media = new Media(API_URL + song.getFile());
            mediaPlayer = new MediaPlayer(media);
        }
        mediaPlayer.play();
    }

    public void stop() {

        if (mediaPlayer != null) {
            mediaPlayer.pause();
        }
    }

    public void resetSong() {

        if (media != null) {
            mediaPlayer.stop();
            media = null;
            mediaPlayer = null;
        }
    }

    public void setSlider(Number newValue) {
        if (mediaPlayer != null) {
            mediaPlayer.seek(mediaPlayer.getStopTime().multiply(newValue.doubleValue() / 100));
            mediaPlayer.setRate(newValue.doubleValue());

        } else {
            System.out.println("No hay canción");
        }
    }

    public void finalCancion() {
        if (mediaPlayer != null) {
            mediaPlayer.seek(mediaPlayer.getStopTime());
        }
    }

    public void inicioCancion() {
        if (mediaPlayer != null) {
            mediaPlayer.seek(mediaPlayer.getStartTime());
        }
    }

    public MainViewController() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(API_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        service = retrofit.create(CancionesAPIService.class);
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        listView.getSelectionModel().selectedItemProperty().addListener((ob, oV, nV) -> {
            Object songSelected = listView.getFocusModel().getFocusedItem();
            Song song = (Song) songSelected;
            if (song != null) {
                Image image = new Image(API_URL + song.getAlbum().getPicture());
                imagen.setImage(image);
                resetSong();
            }
            titleLabel.setText(nV.getTitle());
            artistLabel.setText(nV.album.getArtist().getName());
            fileLabel.setText(nV.getFile());
            playButton.setOnAction(event -> {
                play(song);
            });
            pauseButton.setOnAction(event -> {
                stop();
            });
            inicioCancionButton.setOnAction(event -> {
                inicioCancion();
                play(song);
            });
            finalCancionButton.setOnAction(event -> {
                finalCancion();
                resetSong();
            });
        });
    }

    public void update() {
        asincrono();
        buttonCargarCanciones.setDisable(true);
    }

    private void asincrono() {

        Call<List<Song>> songsCall = service.getSongs(10, "asc");

        songsCall.enqueue(new Callback<List<Song>>() {
            @Override
            public void onResponse(Call<List<Song>> call, Response<List<Song>> response) {
                Platform.runLater(() -> {
                    listView.getItems().removeAll();
                    assert response.body() != null;
                    listView.getItems().addAll(response.body());
                });
            }
            @Override
            public void onFailure(Call<List<Song>> call, Throwable throwable) {
                System.out.println("Network error:" + throwable.getLocalizedMessage());
            }
        });
    }

    @FXML
    public void salir(ActionEvent actionEvent) {
        System.exit(0);
    }
}