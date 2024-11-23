package com.ufcg.bi.models;

import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
public class RequestData {
    private List<Integer> courseCodes;
    private List<String> terms;
}

