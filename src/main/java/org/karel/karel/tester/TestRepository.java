package org.karel.karel.tester;

import java.util.List;

public interface TestRepository {
    List<Test> getTests(int problemId);
}
