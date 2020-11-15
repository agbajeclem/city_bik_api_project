package model;

import java.util.List;

public class ResponseBody {
    public Extra Extra;
    public Location Location;



    public Network Network;

    public model.Network getNetwork() {
        return Network;
    }

    public void setNetwork(model.Network network) {
        Network = network;
    }

    public Root Root;
    public Station Station;

    public model.Extra getExtra() {
        return Extra;
    }

    public void setExtra(model.Extra extra) {
        Extra = extra;
    }

    public model.Location getLocation() {
        return Location;
    }

    public void setLocation(model.Location location) {
        Location = location;
    }



    public model.Root getRoot() {
        return Root;
    }

    public void setRoot(model.Root root) {
        Root = root;
    }

    public model.Station getStation() {
        return Station;
    }

    public void setStation(model.Station station) {
        Station = station;
    }


}

