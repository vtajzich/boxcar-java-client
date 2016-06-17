package net.tajzich.boxcar.client;

/**
 * Created by vtajzich
 */
public enum Sound {

    BeepCrisp("beep-crisp"),
    BeepSoft("beep-soft"),
    BellModern("bell-modern"),
    BellOneTone("bell-one-tone"),
    BellSimple("bell-simple"),
    BellTriple("bell-triple"),
    Bird_1("bird-1"),
    Bird_2("bird-2"),
    Boing("boing"),
    Cash("cash"),
    Clanging("clanging"),
    DetonatorCharge("detonator-charge"),
    DigitalAlarm("digital-alarm"), 
    Done("done"),
    Echo("echo"),
    Flourish("flourish"),
    Harp("harp"),
    Light("light"),
    MagicChime("magic-chime"),
    MagicCoin("magic-coin"),
    Notifier_1("notifier-1"),
    Notifier_2("notifier-2"),
    Notifier_3("notifier-3"),
    OrchestralLong("orchestral-long"),
    OrchestralShort("orchestral-short"),
    Score("score"),
    Success("success"),
    Up("up");

    private String value;

    Sound(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
