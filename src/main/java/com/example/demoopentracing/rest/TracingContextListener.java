package com.example.demoopentracing.rest;

import io.jaegertracing.Configuration;
import io.opentracing.Tracer;
import io.opentracing.util.GlobalTracer;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Singleton;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 *
 * @author lkorenko
 */
@WebListener
public class TracingContextListener implements ServletContextListener {

    @Inject
    private Tracer tracer;

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        GlobalTracer.register(tracer);

    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
    }

    @Produces
    @Singleton
    public static Tracer getTracer() {
        Configuration.SamplerConfiguration samplerConfig = new Configuration.SamplerConfiguration();
        samplerConfig.withParam(1);
        samplerConfig.withType("const");
        Configuration.SenderConfiguration senderConfiguration = new Configuration.SenderConfiguration();
        senderConfiguration.withAgentHost("jaeger");
        senderConfiguration.withAgentPort(5775);
        Configuration.ReporterConfiguration reporterConfig = new Configuration.ReporterConfiguration();
        reporterConfig.withLogSpans(true);
        reporterConfig.withSender(senderConfiguration);
        Configuration config = new Configuration("my");
        config.withReporter(reporterConfig);
        config.withSampler(samplerConfig);
        return config.getTracer();
    }
}
