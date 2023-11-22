package org.example.springboot.web.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.example.springboot.domain.links.Links;
import org.example.springboot.service.LocalNodes.Route;

import java.util.ArrayList;
import java.util.Queue;

@Getter
@RequiredArgsConstructor
public class PathResponseDto {
    private final ArrayList<Route> fastestPath;
    //private final Queue<Links> safestPath;
}
