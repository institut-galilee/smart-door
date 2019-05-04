deps_config := \
	/home/ameni/Documents/IOTF/esp-idf/components/app_trace/Kconfig \
	/home/ameni/Documents/IOTF/esp-idf/components/aws_iot/Kconfig \
	/home/ameni/Documents/IOTF/esp-idf/components/bt/Kconfig \
	/home/ameni/Documents/IOTF/esp-idf/examples/esp32-cam-demo/components/camera/Kconfig \
	/home/ameni/Documents/IOTF/esp-idf/components/driver/Kconfig \
	/home/ameni/Documents/IOTF/esp-idf/components/efuse/Kconfig \
	/home/ameni/Documents/IOTF/esp-idf/components/esp32/Kconfig \
	/home/ameni/Documents/IOTF/esp-idf/components/esp32-camera/Kconfig \
	/home/ameni/Documents/IOTF/esp-idf/components/esp_adc_cal/Kconfig \
	/home/ameni/Documents/IOTF/esp-idf/components/esp_event/Kconfig \
	/home/ameni/Documents/IOTF/esp-idf/components/esp_http_client/Kconfig \
	/home/ameni/Documents/IOTF/esp-idf/components/esp_http_server/Kconfig \
	/home/ameni/Documents/IOTF/esp-idf/components/esp_https_ota/Kconfig \
	/home/ameni/Documents/IOTF/esp-idf/components/espcoredump/Kconfig \
	/home/ameni/Documents/IOTF/esp-idf/components/espmqtt/Kconfig \
	/home/ameni/Documents/IOTF/esp-idf/components/ethernet/Kconfig \
	/home/ameni/Documents/IOTF/esp-idf/components/fatfs/Kconfig \
	/home/ameni/Documents/IOTF/esp-idf/components/freemodbus/Kconfig \
	/home/ameni/Documents/IOTF/esp-idf/components/freertos/Kconfig \
	/home/ameni/Documents/IOTF/esp-idf/components/heap/Kconfig \
	/home/ameni/Documents/IOTF/esp-idf/components/libsodium/Kconfig \
	/home/ameni/Documents/IOTF/esp-idf/components/log/Kconfig \
	/home/ameni/Documents/IOTF/esp-idf/components/lwip/Kconfig \
	/home/ameni/Documents/IOTF/esp-idf/components/mbedtls/Kconfig \
	/home/ameni/Documents/IOTF/esp-idf/components/mdns/Kconfig \
	/home/ameni/Documents/IOTF/esp-idf/components/mqtt/Kconfig \
	/home/ameni/Documents/IOTF/esp-idf/components/nvs_flash/Kconfig \
	/home/ameni/Documents/IOTF/esp-idf/components/openssl/Kconfig \
	/home/ameni/Documents/IOTF/esp-idf/components/pthread/Kconfig \
	/home/ameni/Documents/IOTF/esp-idf/components/spi_flash/Kconfig \
	/home/ameni/Documents/IOTF/esp-idf/components/spiffs/Kconfig \
	/home/ameni/Documents/IOTF/esp-idf/components/tcpip_adapter/Kconfig \
	/home/ameni/Documents/IOTF/esp-idf/components/unity/Kconfig \
	/home/ameni/Documents/IOTF/esp-idf/components/vfs/Kconfig \
	/home/ameni/Documents/IOTF/esp-idf/components/wear_levelling/Kconfig \
	/home/ameni/Documents/IOTF/esp-idf/components/app_update/Kconfig.projbuild \
	/home/ameni/Documents/IOTF/esp-idf/components/bootloader/Kconfig.projbuild \
	/home/ameni/Documents/IOTF/esp-idf/components/esptool_py/Kconfig.projbuild \
	/home/ameni/Documents/IOTF/esp-idf/examples/esp32-cam-demo/main/Kconfig.projbuild \
	/home/ameni/Documents/IOTF/esp-idf/components/partition_table/Kconfig.projbuild \
	/home/ameni/Documents/IOTF/esp-idf/Kconfig

include/config/auto.conf: \
	$(deps_config)

ifneq "$(IDF_TARGET)" "esp32"
include/config/auto.conf: FORCE
endif
ifneq "$(IDF_CMAKE)" "n"
include/config/auto.conf: FORCE
endif

$(deps_config): ;
