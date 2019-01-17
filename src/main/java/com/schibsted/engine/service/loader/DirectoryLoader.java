package com.schibsted.engine.service.loader;

import com.schibsted.engine.domain.ProcessedDirectory;

public interface DirectoryLoader {
    ProcessedDirectory loadDirectory(String path);
}
