package com.codesmasher.smarthomes

import android.Manifest
import android.app.ProgressDialog
import android.bluetooth.*
import android.bluetooth.BluetoothAdapter.LeScanCallback
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.*
import java.util.UUID
import android.widget.*
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
//
//class BluetoothFragment : Fragment() {
//
//    private lateinit var bluetoothAdapter: BluetoothAdapter
//    private lateinit var deviceListView: ListView
//    private lateinit var connectButton: Button
//    private var deviceList: MutableList<BluetoothDevice> = mutableListOf()
//    private lateinit var deviceListAdapter: ArrayAdapter<String>
//    private lateinit var progressDialog: ProgressDialog
//
//    private val REQUEST_ENABLE_BT = 1
//    private val REQUEST_PERMISSION = 2
//    private val deviceNames = mutableListOf<String>()
//    private val SPP_UUID: UUID = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB")
//
//    private val handler = Handler()
//
//
//    private val bluetoothReceiver = object : BroadcastReceiver() {
//        override fun onReceive(context: Context?, intent: Intent?) {
//            when (intent?.action) {
//                BluetoothDevice.ACTION_FOUND -> {
//                    val device: BluetoothDevice? =
//                        intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE)
//                    if (device != null && device.name != null && !deviceNames.contains(device.name)) {
//                        deviceList.add(device)
//                        deviceNames.add(device.name)
//                        deviceListAdapter.add("${device.name} - ${device.address}")
//                    }
//                }
//            }
//        }
//    }
//
//    override fun onCreateView(
//        inflater: LayoutInflater, container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View? {
//        val view = inflater.inflate(R.layout.fragment_bluetooth, container, false)
//
//        deviceListView = view.findViewById(R.id.device_list)
//        connectButton = view.findViewById(R.id.connect_button)
//
//        bluetoothAdapter = BluetoothAdapter.getDefaultAdapter()
//
//        if (bluetoothAdapter == null) {
//            Toast.makeText(requireContext(), "Bluetooth not supported", Toast.LENGTH_LONG).show()
//            return view
//        }
//
//        deviceListAdapter = ArrayAdapter(requireContext(), android.R.layout.simple_list_item_1)
//        deviceListView.adapter = deviceListAdapter
//
//        checkPermissions()
//
//        connectButton.setOnClickListener {
//            scanDevices()
//        }
//
//        deviceListView.setOnItemClickListener { _, _, position, _ ->
//            val device = deviceList[position]
//            connectToDevice(device)
//        }
//
//        return view
//    }
//
//    private fun checkPermissions() {
//        val permissions = mutableListOf<String>()
//
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
//            permissions.add(Manifest.permission.BLUETOOTH_SCAN)
//            permissions.add(Manifest.permission.BLUETOOTH_CONNECT)
//        } else {
//            permissions.add(Manifest.permission.ACCESS_FINE_LOCATION)
//        }
//
//        val missing = permissions.filter {
//            ContextCompat.checkSelfPermission(requireContext(), it) != PackageManager.PERMISSION_GRANTED
//        }
//
//        if (missing.isNotEmpty()) {
//            ActivityCompat.requestPermissions(requireActivity(), missing.toTypedArray(), REQUEST_PERMISSION)
//        }
//    }
//
//    private fun scanDevices() {
//        if (!bluetoothAdapter.isEnabled) {
//            val enableBtIntent = Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE)
//            startActivityForResult(enableBtIntent, REQUEST_ENABLE_BT)
//        }
//
//        deviceList.clear()
//        deviceNames.clear()
//        deviceListAdapter.clear()
//
//        val filter = IntentFilter(BluetoothDevice.ACTION_FOUND)
//        requireActivity().registerReceiver(bluetoothReceiver, filter)
//
//        bluetoothAdapter.startDiscovery()
//
//        handler.postDelayed({
//            bluetoothAdapter.cancelDiscovery()
//            requireActivity().unregisterReceiver(bluetoothReceiver)
//        }, 10000)
//    }
//
//    private fun connectToDevice(device: BluetoothDevice) {
//        progressDialog = ProgressDialog.show(
//            requireContext(),
//            "Connecting...",
//            "Connecting to ${device.name}",
//            true
//        )
//
//        Thread {
//            try {
//                bluetoothAdapter.cancelDiscovery()
////                val socket = device.createRfcommSocketToServiceRecord(device.uuids[0].uuid)
////                socket.connect()
//
//                val socket = device.createRfcommSocketToServiceRecord(SPP_UUID)
//                requireActivity().runOnUiThread {
//                    progressDialog.dismiss()
//                    Toast.makeText(requireContext(), "Connected to ${device.name}", Toast.LENGTH_SHORT).show()
//
//                    // Save socket for communication later
//                    (requireActivity() as MainActivity).bluetoothSocket = socket
//                }
//            } catch (e: Exception) {
//                requireActivity().runOnUiThread {
//                    progressDialog.dismiss()
//                    Toast.makeText(requireContext(), "Connection failed: ${e.message}", Toast.LENGTH_LONG).show()
//                }
//                e.printStackTrace()
//            }
//        }.start()
//    }
//
//    override fun onDestroy() {
//        super.onDestroy()
//        try {
//            requireActivity().unregisterReceiver(bluetoothReceiver)
//        } catch (_: Exception) {}
//    }
//}

//class BluetoothFragment : Fragment() {
//
//    private lateinit var bluetoothAdapter: BluetoothAdapter
//    private lateinit var deviceListView: ListView
//    private lateinit var connectButton: Button
//    private var deviceList: MutableList<BluetoothDevice> = mutableListOf()
//    private lateinit var deviceListAdapter: ArrayAdapter<String>
//    private lateinit var progressDialog: ProgressDialog
//    private lateinit var device: BluetoothDevice
//    private lateinit var socket: BluetoothSocket
//
//    private val REQUEST_ENABLE_BT = 1
//    private val REQUEST_PERMISSION = 2
//    private val deviceNames = mutableListOf<String>()
//    private val SPP_UUID: UUID = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB")
//
//    private val handler = Handler()
//
//    private val bluetoothReceiver = object : BroadcastReceiver() {
//        override fun onReceive(context: Context?, intent: Intent?) {
//            when (intent?.action) {
//                BluetoothDevice.ACTION_FOUND -> {
//                    val device: BluetoothDevice? =
//                        intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE)
//                    if (device != null && device.name != null && !deviceNames.contains(device.name)) {
//                        deviceList.add(device)
//                        deviceNames.add(device.name)
//                        deviceListAdapter.add("${device.name} - ${device.address}")
//                    }
//                }
//            }
//        }
//    }
//
//    override fun onCreateView(
//        inflater: LayoutInflater, container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View? {
//        val view = inflater.inflate(R.layout.fragment_bluetooth, container, false)
//
//        deviceListView = view.findViewById(R.id.device_list)
//        connectButton = view.findViewById(R.id.connect_button)
//
//        bluetoothAdapter = BluetoothAdapter.getDefaultAdapter()
//
//        if (bluetoothAdapter == null) {
//            Toast.makeText(requireContext(), "Bluetooth not supported", Toast.LENGTH_LONG).show()
//            return view
//        }
//
//        deviceListAdapter = ArrayAdapter(requireContext(), android.R.layout.simple_list_item_1)
//        deviceListView.adapter = deviceListAdapter
//
//        checkPermissions()
//
//        connectButton.setOnClickListener {
//            scanDevices()
//        }
//
//        deviceListView.setOnItemClickListener { _, _, position, _ ->
//            val device = deviceList[position]
//            connectToDevice(device)
//        }
//
//        return view
//    }
//
//    private fun checkPermissions() {
//        val permissions = mutableListOf<String>()
//
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
//            permissions.add(Manifest.permission.BLUETOOTH_SCAN)
//            permissions.add(Manifest.permission.BLUETOOTH_CONNECT)
//        } else {
//            permissions.add(Manifest.permission.ACCESS_FINE_LOCATION)
//        }
//
//        val missing = permissions.filter {
//            ContextCompat.checkSelfPermission(requireContext(), it) != PackageManager.PERMISSION_GRANTED
//        }
//
//        if (missing.isNotEmpty()) {
//            ActivityCompat.requestPermissions(requireActivity(), missing.toTypedArray(), REQUEST_PERMISSION)
//        }
//    }
//
//    private fun scanDevices() {
//        if (!bluetoothAdapter.isEnabled) {
//            val enableBtIntent = Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE)
//            startActivityForResult(enableBtIntent, REQUEST_ENABLE_BT)
//        }
//
//        deviceList.clear()
//        deviceNames.clear()
//        deviceListAdapter.clear()
//
//        val filter = IntentFilter(BluetoothDevice.ACTION_FOUND)
//        requireActivity().registerReceiver(bluetoothReceiver, filter)
//
//        bluetoothAdapter.startDiscovery()
//
//        handler.postDelayed({
//            bluetoothAdapter.cancelDiscovery()
//            requireActivity().unregisterReceiver(bluetoothReceiver)
//        }, 10000)
//    }
//
//    private fun connectToDevice(device: BluetoothDevice) {
//        progressDialog = ProgressDialog.show(
//            requireContext(),
//            "Connecting...",
//            "Connecting to ${device.name}",
//            true
//        )
//
//        Thread {
//            try {
//                bluetoothAdapter.cancelDiscovery()
//
//                val socket = device.createRfcommSocketToServiceRecord(SPP_UUID)
//                socket.connect()
//
//                requireActivity().runOnUiThread {
//                    progressDialog.dismiss()
//                    Toast.makeText(requireContext(), "Connected to ${device.name}", Toast.LENGTH_SHORT).show()
//
//                    // Save socket for communication later
//                    (requireActivity() as MainActivity).bluetoothSocket = socket
//                }
//            } catch (e: Exception) {
//                requireActivity().runOnUiThread {
//                    progressDialog.dismiss()
//                    Toast.makeText(requireContext(), "Connection failed: ${e.message}", Toast.LENGTH_LONG).show()
//                }
//                e.printStackTrace()
//            }
//        }.start()
//    }
//
//    override fun onDestroy() {
//        super.onDestroy()
//        try {
//            requireActivity().unregisterReceiver(bluetoothReceiver)
//        } catch (_: Exception) {}
//    }
//}
class BluetoothFragment : Fragment() {

    private lateinit var bluetoothAdapter: BluetoothAdapter
    private lateinit var deviceListView: ListView
    private lateinit var connectButton: Button
    private var deviceList: MutableList<BluetoothDevice> = mutableListOf()
    private lateinit var deviceListAdapter: ArrayAdapter<String>
    private lateinit var progressDialog: ProgressDialog
    private lateinit var device: BluetoothDevice
    private lateinit var socket: BluetoothSocket

    private val REQUEST_ENABLE_BT = 1
    private val REQUEST_PERMISSION = 2
    private val deviceNames = mutableListOf<String>()
    private val SPP_UUID: UUID = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB")

    private val handler = Handler()

    private val bluetoothReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            when (intent?.action) {
                BluetoothDevice.ACTION_FOUND -> {
                    val device: BluetoothDevice? =
                        intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE)
                    if (device != null && device.name != null && !deviceNames.contains(device.name)) {
                        deviceList.add(device)
                        deviceNames.add(device.name)
                        deviceListAdapter.add("${device.name} - ${device.address}")
                    }
                }
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_bluetooth, container, false)

        deviceListView = view.findViewById(R.id.device_list)
        connectButton = view.findViewById(R.id.connect_button)

        bluetoothAdapter = BluetoothAdapter.getDefaultAdapter()

        if (bluetoothAdapter == null) {
            Toast.makeText(requireContext(), "Bluetooth not supported", Toast.LENGTH_LONG).show()
            return view
        }

        deviceListAdapter = ArrayAdapter(requireContext(), android.R.layout.simple_list_item_1)
        deviceListView.adapter = deviceListAdapter

        checkPermissions()

        connectButton.setOnClickListener {
            scanDevices()
        }

        deviceListView.setOnItemClickListener { _, _, position, _ ->
            val device = deviceList[position]
            connectToDevice(device)
        }

        return view
    }

    private fun checkPermissions() {
        val permissions = mutableListOf<String>()

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            permissions.add(Manifest.permission.BLUETOOTH_SCAN)
            permissions.add(Manifest.permission.BLUETOOTH_CONNECT)
        } else {
            permissions.add(Manifest.permission.ACCESS_FINE_LOCATION)
        }

        val missing = permissions.filter {
            ContextCompat.checkSelfPermission(requireContext(), it) != PackageManager.PERMISSION_GRANTED
        }

        if (missing.isNotEmpty()) {
            ActivityCompat.requestPermissions(requireActivity(), missing.toTypedArray(), REQUEST_PERMISSION)
        }
    }

    private fun scanDevices() {
        if (!bluetoothAdapter.isEnabled) {
            val enableBtIntent = Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE)
            startActivityForResult(enableBtIntent, REQUEST_ENABLE_BT)
        }

        deviceList.clear()
        deviceNames.clear()
        deviceListAdapter.clear()

        val filter = IntentFilter(BluetoothDevice.ACTION_FOUND)
        requireActivity().registerReceiver(bluetoothReceiver, filter)

        bluetoothAdapter.startDiscovery()

        handler.postDelayed({
            bluetoothAdapter.cancelDiscovery()
            requireActivity().unregisterReceiver(bluetoothReceiver)
        }, 10000)
    }

    private fun connectToDevice(device: BluetoothDevice) {
        progressDialog = ProgressDialog.show(
            requireContext(),
            "Connecting...",
            "Connecting to ${device.name}",
            true
        )

        Thread {
            try {
                bluetoothAdapter.cancelDiscovery()

                val socket = device.createRfcommSocketToServiceRecord(SPP_UUID)
                socket.connect()

                // Access the MainActivity and save the socket for communication
                val mainActivity = requireActivity() as MainActivity
                mainActivity.bluetoothSocket = socket

                requireActivity().runOnUiThread {
                    progressDialog.dismiss()
                    Toast.makeText(requireContext(), "Connected to ${device.name}", Toast.LENGTH_SHORT).show()

                    // You can now use the socket to communicate with the Bluetooth device
                }
            } catch (e: Exception) {
                requireActivity().runOnUiThread {
                    progressDialog.dismiss()
                    Toast.makeText(requireContext(), "Connection failed: ${e.message}", Toast.LENGTH_LONG).show()
                }
                e.printStackTrace()
            }
        }.start()
    }

    override fun onDestroy() {
        super.onDestroy()
        try {
            requireActivity().unregisterReceiver(bluetoothReceiver)
        } catch (_: Exception) {}
    }
}
