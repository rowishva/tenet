package com.tenet.web.rest.admin.dto.request;


import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModelProperty;
import java.util.ArrayList;
import java.util.List;

public class ProfileListResponse   {
    @JsonProperty("content")
    private List<ProfileRequestResponse> content = null;

    @JsonProperty("pageable")
    private org.springframework.data.domain.Pageable pageable;

    @JsonProperty("first")
    private Boolean first;

    @JsonProperty("last")
    private Boolean last;

    @JsonProperty("numberOfElements")
    private Integer numberOfElements;

    @JsonProperty("size")
    private Integer size;

    @JsonProperty("totalElements")
    private Integer totalElements;

    @JsonProperty("totalPages")
    private Integer totalPages;

    public ProfileListResponse content(List<ProfileRequestResponse> content) {
        this.content = content;
        return this;
    }

    public ProfileListResponse addContentItem(ProfileRequestResponse contentItem) {
        if (this.content == null) {
            this.content = new ArrayList<>();
        }
        this.content.add(contentItem);
        return this;
    }

    /**
     * Get content
     * @return content
     */
    @ApiModelProperty(value = "")
    public List<ProfileRequestResponse> getContent() {
        return content;
    }
    public void setContent(List<ProfileRequestResponse> content) {
        this.content = content;
    }
    public ProfileListResponse pageable(org.springframework.data.domain.Pageable pageable) {
        this.pageable = pageable;
        return this;
    }

    /**
     * Get pageable
     * @return pageable
     */
    @ApiModelProperty(value = "")

    public org.springframework.data.domain.Pageable getPageable() {
        return pageable;
    }

    public void setPageable(org.springframework.data.domain.Pageable pageable) {
        this.pageable = pageable;
    }

    public ProfileListResponse first(Boolean first) {
        this.first = first;
        return this;
    }

    /**
     * Get first
     * @return first
     */
    @ApiModelProperty(value = "")


    public Boolean getFirst() {
        return first;
    }

    public void setFirst(Boolean first) {
        this.first = first;
    }

    public ProfileListResponse last(Boolean last) {
        this.last = last;
        return this;
    }

    /**
     * Get last
     * @return last
     */
    @ApiModelProperty(value = "")


    public Boolean getLast() {
        return last;
    }

    public void setLast(Boolean last) {
        this.last = last;
    }

    public ProfileListResponse numberOfElements(Integer numberOfElements) {
        this.numberOfElements = numberOfElements;
        return this;
    }

    /**
     * Get numberOfElements
     * @return numberOfElements
     */
    @ApiModelProperty(value = "")


    public Integer getNumberOfElements() {
        return numberOfElements;
    }

    public void setNumberOfElements(Integer numberOfElements) {
        this.numberOfElements = numberOfElements;
    }

    public ProfileListResponse size(Integer size) {
        this.size = size;
        return this;
    }

    /**
     * Get size
     * @return size
     */
    @ApiModelProperty(value = "")


    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public ProfileListResponse totalElements(Integer totalElements) {
        this.totalElements = totalElements;
        return this;
    }

    /**
     * Get totalElements
     * @return totalElements
     */
    @ApiModelProperty(value = "")


    public Integer getTotalElements() {
        return totalElements;
    }

    public void setTotalElements(Integer totalElements) {
        this.totalElements = totalElements;
    }

    public ProfileListResponse totalPages(Integer totalPages) {
        this.totalPages = totalPages;
        return this;
    }

    /**
     * Get totalPages
     * @return totalPages
     */
    @ApiModelProperty(value = "")


    public Integer getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(Integer totalPages) {
        this.totalPages = totalPages;
    }


    @Override
    public boolean equals(java.lang.Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ProfileListResponse ProfileListResponse = (ProfileListResponse) o;
        return Objects.equals(this.content, ProfileListResponse.content) &&
                Objects.equals(this.pageable, ProfileListResponse.pageable) &&
                Objects.equals(this.first, ProfileListResponse.first) &&
                Objects.equals(this.last, ProfileListResponse.last) &&
                Objects.equals(this.numberOfElements, ProfileListResponse.numberOfElements) &&
                Objects.equals(this.size, ProfileListResponse.size) &&
                Objects.equals(this.totalElements, ProfileListResponse.totalElements) &&
                Objects.equals(this.totalPages, ProfileListResponse.totalPages);
    }

    @Override
    public int hashCode() {
        return Objects.hash(content, pageable, first, last, numberOfElements, size, totalElements, totalPages);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class ProfileListResponse {\n");

        sb.append("    content: ").append(toIndentedString(content)).append("\n");
        sb.append("    pageable: ").append(toIndentedString(pageable)).append("\n");
        sb.append("    first: ").append(toIndentedString(first)).append("\n");
        sb.append("    last: ").append(toIndentedString(last)).append("\n");
        sb.append("    numberOfElements: ").append(toIndentedString(numberOfElements)).append("\n");
        sb.append("    size: ").append(toIndentedString(size)).append("\n");
        sb.append("    totalElements: ").append(toIndentedString(totalElements)).append("\n");
        sb.append("    totalPages: ").append(toIndentedString(totalPages)).append("\n");
        sb.append("}");
        return sb.toString();
    }

    /**
     * Convert the given object to string with each line indented by 4 spaces
     * (except the first line).
     */
    private String toIndentedString(java.lang.Object o) {
        if (o == null) {
            return "null";
        }
        return o.toString().replace("\n", "\n    ");
    }
}

