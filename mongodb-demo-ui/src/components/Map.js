import { useEffect, useRef, useState } from "react";

const initialPoint = { lat: 35.95, lng: 128.25 };

const Map = ({
  markers,
  handleMarkerClick,
  width = "500px",
  height = "500px",
  center = null,
}) => {
  const navermaps = window.naver.maps;
  const mapArea = useRef(null);
  const [map, setMap] = useState(null);

  const markerClickEvent = (e, item) => {
    changeMapCenterAndZoom({ center: e.overlay.position, zoom: 19 });

    handleMarkerClick(item);
  };

  const changeMapCenterAndZoom = ({ center, zoom }) => {
    map.morph(center, zoom);
  };

  const makeMarker = (position, name) => {
    const markerOptions = {
      position: position,
      map: map,
      icon: {
        content: `<div class='map-marker'>${name}</div>`,
        size: new navermaps.Size(100, 35),
        anchor: new navermaps.Point(11, 35),
      },
    };

    return new navermaps.Marker(markerOptions);
  };

  const printMarkers = () => {
    markers?.forEach((item) => {
      const marker = makeMarker(
        new navermaps.LatLng(item.location.y, item.location.x),
        item.name
      );

      new navermaps.Event.addListener(marker, "click", (e) =>
        markerClickEvent(e, item)
      );
    });
  };

  useEffect(() => {
    const map = new navermaps.Map(mapArea.current, {
      zoom: 6,
      center: new navermaps.LatLng(initialPoint.lat, initialPoint.lng),
    });

    setMap(map);
  }, []);

  useEffect(() => {
    if (map && markers) {
      printMarkers();
    }
  }, [map, markers]);

  useEffect(() => {
    if (map && center) {
      changeMapCenterAndZoom({ center: center, zoom: 19 });
    }
  }, [center]);

  return (
    <div style={{ width: width, height: height }}>
      <div ref={mapArea} style={{ width: "100%", height: "100%" }}></div>
    </div>
  );
};

export default Map;
