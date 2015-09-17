package com.junjunguo.resumedownload;

/**
 * This file is part of ResumeDownload
 * <p>
 * Created by GuoJunjun <junjunguo.com> on September 14, 2015.
 */
public interface DownloadListener {
    /**
     * downloadFile progress value
     *
     * @param value
     */
    void progressUpdate(Message value);
}
