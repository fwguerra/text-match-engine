package com.schibsted.engine.service.reader;

import com.schibsted.engine.domain.Arguments;

public interface ArgumentsReader {
    Arguments read(String[] arguments);
}
