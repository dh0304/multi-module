package com.cafegory.db.cafe.cafe;

import com.cafegory.db.BaseEntity;
import com.cafegory.db.cafe.keyword.CafeKeywordEntity;
import com.cafegory.db.cafe.menu.MenuEntity;
import com.cafegory.db.cafe.tag.CafeCafeTagEntity;
import com.cafegory.db.cafe.tag.CafeTagEntity;
import com.cafegory.db.study.study.CafeStudyEntity;
import com.cafegory.domain.cafe.domain.Address;
import com.cafegory.domain.cafe.domain.Cafe;
import com.cafegory.domain.cafe.domain.CafeId;
import com.cafegory.domain.cafe.domain.Menu;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Where;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Builder
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
@Setter
@Where(clause = "deleted_date IS NULL")
@Table(name = "cafe")
public class CafeEntity extends BaseEntity {

	@Id
	@GeneratedValue
	@Column(name = "cafe_id")
	private Long id;

	private String name;

	private String mainImageUrl;

	@Embedded
	private AddressEmbeddable address;

	private String sns;

	@Builder.Default
	@OneToMany(mappedBy = "cafe")
	private List<CafeKeywordEntity> cafeKeywords = new ArrayList<>();

	@Builder.Default
	@OneToMany(mappedBy = "cafe")
	private List<CafeCafeTagEntity> cafeCafeTags = new ArrayList<>();

	@Builder.Default
	@OneToMany(mappedBy = "cafe")
	private List<MenuEntity> menus = new ArrayList<>();

	@Builder.Default
	@OneToMany(mappedBy = "cafe")
	private List<CafeStudyEntity> cafeStudies = new ArrayList<>();

	public CafeEntity(Long id) {
		this.id = id;
	}

	public Cafe toCafe() {
		return Cafe.builder()
			.id(new CafeId(this.id))
			.name(this.name)
			.imgUrl(this.mainImageUrl)
			.sns(this.sns)
			.cafeTagTypes(
				this.cafeCafeTags.stream()
					.map(CafeCafeTagEntity::getCafeTag)
					.filter(Objects::nonNull)
					.map(CafeTagEntity::getType)
					.collect(Collectors.toList())
			)
			.address(
				Address.builder()
					.fullAddress(this.address.getFullAddress())
					.region(this.address.getRegion())
					.build()
			)
			.menus(
				this.menus.stream()
					.map(menu ->
						Menu.builder()
							.name(menu.getName())
							.price(menu.getPrice())
							.build()
					)
					.collect(Collectors.toList())
			)
			.build();
	}
}
