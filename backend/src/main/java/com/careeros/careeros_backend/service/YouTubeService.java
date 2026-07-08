package com.careeros.careeros_backend.service;

import com.careeros.careeros_backend.dto.YouTubeVideoResponse;
import com.careeros.careeros_backend.model.LearningVideo;
import com.careeros.careeros_backend.repository.LearningVideoRepository;
import lombok.RequiredArgsConstructor;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class YouTubeService {

    @Value("${youtube.api.key}")
    private String apiKey;

    private final LearningVideoRepository
            learningVideoRepository;

    private final RestTemplate
            restTemplate = new RestTemplate();

    public List<YouTubeVideoResponse>
    searchVideos(
            String skill
    ) {

        var cached =
                learningVideoRepository
                        .findBySkillIgnoreCase(
                                skill
                        );

        if(cached.isPresent()) {

            LearningVideo video =
                    cached.get();

            List<YouTubeVideoResponse>
                    results =
                    new ArrayList<>();

            for(
                    int i = 0;
                    i < video.getTitles().size();
                    i++
            ) {

                results.add(

                        YouTubeVideoResponse
                                .builder()
                                .title(
                                        video.getTitles().get(i)
                                )
                                .channel(
                                        video.getChannels().get(i)
                                )
                                .thumbnail(
                                        video.getThumbnails().get(i)
                                )
                                .videoUrl(
                                        video.getUrls().get(i)
                                )
                                .build()

                );
            }

            return results;
        }

        String url =
                "https://www.googleapis.com/youtube/v3/search"
                + "?part=snippet"
                + "&maxResults=5"
                + "&q=" + skill
                + "&type=video"
                + "&key=" + apiKey;

        String response =
                restTemplate.getForObject(
                        url,
                        String.class
                );

                System.out.println(url);
System.out.println(response);

        JSONObject json =
                new JSONObject(response);

        if(!json.has("items"))
{
    return List.of(
            YouTubeVideoResponse
                    .builder()
                    .title(
                            "YouTube videos unavailable"
                    )
                    .channel("")
                    .thumbnail("")
                    .videoUrl("")
                    .build()
    );
}

JSONArray items =
        json.getJSONArray("items");

        List<YouTubeVideoResponse>
                videos =
                new ArrayList<>();

        List<String> titles =
                new ArrayList<>();

        List<String> channels =
                new ArrayList<>();

        List<String> thumbnails =
                new ArrayList<>();

        List<String> urls =
                new ArrayList<>();

        for(
                int i = 0;
                i < items.length();
                i++
        ) {

            JSONObject item =
                    items.getJSONObject(i);

            String videoId =
                    item
                    .getJSONObject("id")
                    .getString("videoId");

            JSONObject snippet =
                    item.getJSONObject(
                            "snippet"
                    );

            String title =
                    snippet.getString(
                            "title"
                    );

            String channel =
                    snippet.getString(
                            "channelTitle"
                    );

            String thumbnail =
                    snippet
                    .getJSONObject(
                            "thumbnails"
                    )
                    .getJSONObject(
                            "high"
                    )
                    .getString(
                            "url"
                    );

            String videoUrl =
                    "https://www.youtube.com/watch?v="
                    + videoId;

            videos.add(

                    YouTubeVideoResponse
                            .builder()
                            .title(title)
                            .channel(channel)
                            .thumbnail(thumbnail)
                            .videoUrl(videoUrl)
                            .build()

            );

            titles.add(title);
            channels.add(channel);
            thumbnails.add(thumbnail);
            urls.add(videoUrl);
        }

        learningVideoRepository.save(

                LearningVideo
                        .builder()
                        .skill(skill)
                        .titles(titles)
                        .channels(channels)
                        .thumbnails(thumbnails)
                        .urls(urls)
                        .build()

        );

        return videos;
    }
}