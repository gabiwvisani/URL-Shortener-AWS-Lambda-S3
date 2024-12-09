package com.rocketseat.createUrlShortner;

import lombok.*;


@AllArgsConstructor
@Getter
@Setter
public class UrlData {
    private String originalUrl;
    private long experationTime;

}
