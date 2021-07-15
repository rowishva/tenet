package com.tenet.web.rest.common.config;

import org.modelmapper.Converter;
import org.modelmapper.spi.MappingContext;

import com.tenet.web.rest.common.enums.ProfileStatus;
import com.tenet.web.rest.common.enums.SpecialNeeds;

public class ModelMapEnumConverter {

	public static Converter<Integer, SpecialNeeds> convertIntToSpecialNeeds() {
		return new Converter<Integer, SpecialNeeds>() {
			@Override
			public SpecialNeeds convert(MappingContext<Integer, SpecialNeeds> context) {
				switch (context.getSource()) {
				case 0:
					return SpecialNeeds.NONE;
				case 1:
					return SpecialNeeds.ELDERLY;
				case 2:
					return SpecialNeeds.OKU;
				case 3:
					return SpecialNeeds.SPECIALNEED_HELPER;
				default:
					return SpecialNeeds.NONE;
				}
			}
		};
	}

	public static Converter<SpecialNeeds, Integer> convertSpecialNeedsToInt() {
		return new Converter<SpecialNeeds, Integer>() {
			@Override
			public Integer convert(MappingContext<SpecialNeeds, Integer> context) {
				switch (context.getSource()) {
				case NONE:
					return SpecialNeeds.NONE.getCode();
				case ELDERLY:
					return SpecialNeeds.ELDERLY.getCode();
				case OKU:
					return SpecialNeeds.OKU.getCode();
				case SPECIALNEED_HELPER:
					return SpecialNeeds.SPECIALNEED_HELPER.getCode();
				default:
					return SpecialNeeds.NONE.getCode();
				}
			}
		};
	}

	public static Converter<Integer, ProfileStatus> convertIntToProfileStatus() {
		return new Converter<Integer, ProfileStatus>() {
			@Override
			public ProfileStatus convert(MappingContext<Integer, ProfileStatus> context) {
				switch (context.getSource()) {
				case 0:
					return ProfileStatus.ACTIVE;
				case 1:
					return ProfileStatus.CLOSED;
				case 2:
					return ProfileStatus.DISABLED;
				default:
					return ProfileStatus.BLACKLIST;
				}
			}
		};
	}

	public static Converter<ProfileStatus, Integer> convertProfileStatusToInt() {
		return new Converter<ProfileStatus, Integer>() {
			@Override
			public Integer convert(MappingContext<ProfileStatus, Integer> context) {
				switch (context.getSource()) {
				case ACTIVE:
					return ProfileStatus.ACTIVE.getCode();
				case CLOSED:
					return ProfileStatus.CLOSED.getCode();
				case DISABLED:
					return ProfileStatus.DISABLED.getCode();
				default:
					return ProfileStatus.BLACKLIST.getCode();
				}
			}
		};
	}

}
