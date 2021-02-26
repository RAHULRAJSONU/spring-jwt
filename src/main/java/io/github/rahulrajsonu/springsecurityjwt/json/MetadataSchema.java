package io.github.rahulrajsonu.springsecurityjwt.json;

import com.google.gson.annotations.SerializedName;

import java.util.List;
import java.util.Map;
import java.util.Objects;

public class MetadataSchema {

    @SerializedName("segment-name")
    private String segmentName;

    @SerializedName("channels")
    private List<Map<String,String>> channels;

    public String getSegmentName() {
        return segmentName;
    }

    public void setSegmentName(String segmentName) {
        this.segmentName = segmentName;
    }

    public List<Map<String, String>> getChannels() {
        return channels;
    }

    public void setChannels(List<Map<String, String>> channels) {
        this.channels = channels;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MetadataSchema that = (MetadataSchema) o;
        return segmentName.equals(that.segmentName) && channels.equals(that.channels);
    }

    @Override
    public int hashCode() {
        return Objects.hash(segmentName, channels);
    }

    @Override
    public String toString() {
        return "MetadataSchema{" +
                "segmentName='" + segmentName + '\'' +
                ", channels=" + channels +
                '}';
    }
}
