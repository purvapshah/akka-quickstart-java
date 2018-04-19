package com.lightbend.akka.sample;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import com.lightbend.akka.sample.Greeter.*;
import kamon.Kamon;
import kamon.prometheus.PrometheusReporter;
import kamon.zipkin.ZipkinReporter;

import java.io.IOException;

public class AkkaQuickstart {

  public static void main(String[] args) {

    // init kamon reporter
    Kamon.loadReportersFromConfig();

    Kamon.addReporter(new ZipkinReporter());
    Kamon.addReporter(new PrometheusReporter());

    final ActorSystem system = ActorSystem.create("helloakka");
    try {
      //#create-actors
      final ActorRef printerActor =
        system.actorOf(Printer.props(), "printerActor");
      final ActorRef howdyGreeter = 
        system.actorOf(Greeter.props("Howdy", printerActor), "howdyGreeter");
      final ActorRef helloGreeter = 
        system.actorOf(Greeter.props("Hello", printerActor), "helloGreeter");
      final ActorRef goodDayGreeter = 
        system.actorOf(Greeter.props("Good day", printerActor), "goodDayGreeter");
      //#create-actors

      System.out.println(">>> Press ENTER to exit <<<");
      System.in.read();

      int i = 0;

      while (true) {
        //#main-send-messages
        howdyGreeter.tell(new WhoToGreet("Akka"), ActorRef.noSender());
        howdyGreeter.tell(new Greet(), ActorRef.noSender());

        howdyGreeter.tell(new WhoToGreet("Lightbend"), ActorRef.noSender());
        howdyGreeter.tell(new Greet(), ActorRef.noSender());

        helloGreeter.tell(new WhoToGreet("Java"), ActorRef.noSender());
        helloGreeter.tell(new Greet(), ActorRef.noSender());


        goodDayGreeter.tell(new WhoToGreet("Play"), ActorRef.noSender());
        goodDayGreeter.tell(new Greet(), ActorRef.noSender());
        //#main-send-messages

        Thread.sleep(1000);

        i++;
      }



    } catch (Exception ioe) {
    } finally {
      system.terminate();
    }
  }
}
