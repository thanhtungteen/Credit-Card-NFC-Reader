/*
 * Copyright (C) 2019 MILLAU Julien
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.pro100svitlo.creditCardNfcReader.parser;

import com.pro100svitlo.creditCardNfcReader.exception.CommunicationException;

/**
 * Interface for provider for transmit command to card
 *
 * @author MILLAU Julien
 *
 */
public interface IProvider {

	/**
	 * Method used to transmit and receive card response
	 *
	 * @param pCommand
	 *            command to send to card
	 * @return byte array returned by card
	 * @throws CommunicationException communication error
	 */
	byte[] transceive(byte[] pCommand) throws CommunicationException;

	/**
	 * Method used to get the card ATR or ATS
	 * @return the card Answer to reset or Answer to select
	 */
	byte[] getAt();

}
