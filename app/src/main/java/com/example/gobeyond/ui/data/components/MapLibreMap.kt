package com.example.gobeyond.ui.data.components

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView
import org.maplibre.android.maps.MapView
import org.maplibre.android.maps.OnMapReadyCallback
import org.maplibre.android.maps.Style
import org.maplibre.android.geometry.LatLng
import org.maplibre.android.maps.MapLibreMap

@Composable
fun MapLibreMap(
    lat: Double,
    lon: Double,
    modifier: Modifier = Modifier
) {
    AndroidView(
        modifier = modifier,
        factory = { context ->
            val mapView = MapView(context)

            mapView.getMapAsync(object : OnMapReadyCallback {
                override fun onMapReady(mapLibreMap: MapLibreMap) {

                    mapLibreMap.setStyle(Style.Builder().fromUri("https://tiles.openfreemap.org/styles/liberty"))

                    mapLibreMap.moveCamera(
                        org.maplibre.android.camera.CameraUpdateFactory.newLatLngZoom(
                            LatLng(lat, lon),
                            12.0  // default zoom
                        )
                    )
                }
            })

            mapView
        }
    )
}