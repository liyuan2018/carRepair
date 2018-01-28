package com.cys.observer;

/**
 * Created by liyuan on 2018/1/28.
 * 用多线程池记录日志：
 * 1、增加效率，减少每次访问由于记录日志所产生的等待消耗
 * 2、另开一个线程将游离于当前事物之外，不会因为当前事物错误而影响记录日志，也不会因为记录日志错误而影响业务事物
 */
import com.cys.model.LoggerModel;
import com.cys.service.ILoggerTarget;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TargetObserver {
    private List<ILoggerTarget> targets;
    private ExecutorService pool = Executors.newFixedThreadPool (5);

    private static interface Singleton {
        final TargetObserver INSTANCE = new TargetObserver();
    }

    public static TargetObserver getInstance() {
        return Singleton.INSTANCE;
    }

    public void info(LoggerModel m) {
        Runnable runner = new Executor(m, true);
        pool.execute(runner);
    }

    public void error(LoggerModel m) {
        Runnable runner = new Executor(m, false);
        pool.execute(runner);
    }

    class Executor implements Runnable {
        private final LoggerModel model;
        private final boolean isInfo;

        Executor(LoggerModel m, boolean isInfo) {
            this.model = m;
            this.isInfo = isInfo;
        }

        @Override
        public void run() {
            targets = TargetFactory.getFactory().getTargets(model.getClassName());
            if (null == targets) {
                return;
            }

            for (int i=0; i<targets.size(); i++) {
                ILoggerTarget target = targets.get(i);
                if (isInfo) {
                    target.info(model);
                } else {
                    target.error(model);
                }
            }
        }
    }
}

