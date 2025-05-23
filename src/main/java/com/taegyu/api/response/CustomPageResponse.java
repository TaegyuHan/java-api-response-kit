package com.taegyu.api.response;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.List;

/**
 * 페이지네이션된 데이터를 위한 응답 클래스입니다.
 * 페이지 정보와 데이터 목록을 포함합니다.
 *
 * @param <T> 페이지에 포함된 데이터의 타입
 */
@Getter
@Setter
@SuperBuilder
public class CustomPageResponse<T> {
    /**
     * 페이지에 포함된 데이터 목록
     */
    private List<T> content;

    /**
     * 현재 페이지 번호 (0부터 시작)
     */
    private int page;

    /**
     * 페이지당 데이터 개수
     */
    private int size;

    /**
     * 전체 데이터 개수
     */
    private long totalElements;

    /**
     * 전체 페이지 수
     */
    private int totalPages;

    /**
     * 다음 페이지 존재 여부
     */
    private boolean hasNext;

    /**
     * 이전 페이지 존재 여부
     */
    private boolean hasPrevious;

    /**
     * 페이지네이션된 데이터를 생성합니다.
     *
     * @param content 페이지에 포함된 데이터 목록
     * @param page 현재 페이지 번호
     * @param size 페이지당 데이터 개수
     * @param totalElements 전체 데이터 개수
     * @param <T> 페이지에 포함된 데이터의 타입
     * @return 생성된 페이지 응답 객체
     */
    public static <T> CustomPageResponse<T> of(List<T> content, int page, int size, long totalElements) {
        int totalPages = (int) Math.ceil((double) totalElements / size);
        return CustomPageResponse.<T>builder()
                .content(content)
                .page(page)
                .size(size)
                .totalElements(totalElements)
                .totalPages(totalPages)
                .hasNext(page < totalPages - 1)
                .hasPrevious(page > 0)
                .build();
    }
} 